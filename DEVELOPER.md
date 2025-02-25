# 项目说明

首先必须说的是本人学艺不精，代码写的很烂，对 Kotlin、Android 都只有简单的了解。至于设计模式（Design pattern）之类的，
更是一头雾水。因此，我不会把代码写复杂，初学者有兴趣的话可以看一下，高手就不必了，学不到什么😓。

参考了官方示例项目[Now in Android](https://github.com/android/nowinandroid) 来搭建，照猫画虎，当然是简化版，没它那么复杂。

## 基础

开发语言：

[Kotlin](https://kotlinlang.org/docs/home.html)

Android UI:

[Jetpack Compose](https://developer.android.google.cn/develop/ui/compose/documentation?hl=zh-cn)

数据持久化：

[Room](https://developer.android.google.cn/training/data-storage/room?hl=zh-cn)

[DataStore](https://developer.android.google.cn/topic/libraries/architecture/datastore?hl=zh-cn)

依赖注入： 

[Hilt](https://developer.android.google.cn/training/dependency-injection/hilt-android?hl=zh-cn)

网络请求：

[OkHttp](https://square.github.io/okhttp/)

[Retrofit](https://square.github.io/retrofit/)

图像加载；

[Coil](https://coil-kt.github.io/coil/)

权限请求：

[Jetpack Compose Permissions](https://google.github.io/accompanist/permissions/)

页面导航：

[Navigation Compose](https://developer.android.google.cn/guide/navigation/principles?hl=zh-cn)

## 项目解析

开始于 `MainActivity.kt`，引入 Navigation 导航：

```kotlin
import com.hefengbao.jingmo.route.AppNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val destination = intent?.getStringExtra("destination")
        
        setContent {
            val appNavController = rememberNavController()
            
            AppTheme{
                AppNavHost(navController = appNavController)
            }

            destination?.let {
                appNavController.navigate(it)
            }
        }
    }
}
```

页面之间的跳转逻辑定义在 `route/AppNavHost.kt`。

跳转到一个 UI 界面，大致调用逻辑 UI -> ViewModel -> Repository-> Database(Preference)

UI 定义在 `ui` 目录下

一个典型的页面定义 `link/nav/LinkIndexNav.kt`、`link/LindexIndexScreen.kt`、`link/LinkIndexViewModel.kt`。

![](screenshot/d/1.jpg)

点击首页的“链接”按钮,`route/AppNavHost.kt` 定义的逻辑：

```kotlin
homeGraph(
    onLinksClick = { navController.navigateToLinkIndexScreen() },
    nestGraph = {
        linkIndexScreen(
            onBackClick = navController::navigateUp
        )
    }
)
```

`navigateToLinkIndexScreen()` 和 `linkIndexScreen()` 定义在 `link/nav/LinkIndexNav.kt`:

```kotlin
private const val ROUTE = "link_index"

fun NavController.navigateToLinkIndexScreen() {
    this.navigate(ROUTE)
}

fun NavGraphBuilder.linkIndexScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = ROUTE
    ) {
        LinkIndexRoute(
            onBackClick = onBackClick
        )
    }
}
```

这里导航的目的地`link_index`很简单，稍微复杂一点是传递参数， 可参考 `chinese/quote/nav/QuoteShowNav.kt`。

`NavGraphBuilder.xx` 顾名思义，定义导航图，简单一点如上数代码，复杂一点的如 `chinese/quote/nav/QuoteIndexNav.kt`:

```kotlin
fun NavGraphBuilder.chineseQuoteIndexGraph(
    onBackClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    onSearchClick: () -> Unit,
    nestGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        startDestination = ROUTE,
        route = ROUTE_GRAPH
    ) {
        composable(ROUTE) {
            QuoteIndexRoute(
                onBackClick = onBackClick,
                onBookmarksClick = onBookmarksClick,
                onReadMoreClick = onReadMoreClick,
                onSearchClick = onSearchClick
            )
        }

        nestGraph()
    }
}
```

`nestGraph` 顾名思义，是嵌套的意思，如果页面的访问顺序永远都是 A -> B -> C, 不存在 AA -> C, 那么则可以定义这样的嵌套结构，比较明了。

`LinkIndexRoute()` 定义在 `link/LindexIndexScreen.kt`:

```kotlin
@Composable
fun LinkIndexRoute(
    viewModel: LinkIndexViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    
}
```

`LinkIndexViewModel` 定义在 `link/LinkIndexViewModel`:

```kotlin
@HiltViewModel
class LinkIndexViewModel @Inject constructor(
    private val repository: LinksRepository
) : ViewModel() {
    
}
```

`LinksRepository` 定义在 `data/repository/LinksRepository`, 这是一个接口（interface）， 实现为 `LinksRepositoryImpl`

```kotlin
class LinksRepositoryImpl @Inject constructor() : LinksRepository {
    
}
```

复杂一点的场景，是实现网络请求数据和数据库操作。

目前 App 以离线数据的方式运行，从网络“同步数据”，或者下载 json 格式的数据然后导入，数据保存在本地 Room（SQLite） 数据库。

`repository/settings/NetworkDatasourceRepository` 定义了同步数据相关的接口：

```kotlin
interface NetworkDatasourceRepository {
    // 以同步歌词为例
    suspend fun insertChineseLyric(entity: LyricEntity)
    suspend fun syncChineseLyrics(version: Int): Result<List<Lyric>>
}
```

实现为 `repository/settings/NetworkDatasourceRepositoryImpl`:

```kotlin
class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun insertChineseLyric(entity: LyricEntity) =
        database.lyricDao().insert(entity)

    override suspend fun syncChineseLyrics(version: Int): Result<List<Lyric>> = safeApiCall {
        network.chineseLyrics(version)
    }
}
```

`Network` 定义在 `data/network/Network.kt`

```kotlin
interface Network {
    suspend fun chineseLyrics(version: Int): List<Lyric>
}
```

具体实现为 `data/network/retrofit/NetworkImpl`

```kotlin
@Singleton
class NetworkImpl @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory
) : Network {

    private val networkApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        ).build()
        .create(Api::class.java)
    
    override suspend fun chineseLyrics(version: Int): List<Lyric> =
        networkApi.chineseLyrics(version)
}
```

`data/network/retrofit/Api` 定义 Retrofit 请求接口：

```kotlin
interface Api {
    @GET("chinese_lyrics_v{version}.json")
    suspend fun chineseLyrics(
        @Path("version") version: Int,
    ): List<Lyric>
}
```

获取的网络数据解析为 `List<Lyric>`, `Lyric` 定义在 `data/model/chinese/Lyric.kt`:

```kotlin
@Serializable
data class Lyric(
    val id: Int,
    val title: String,
    val writer: String?,
    val singer: String?,
    val content: String
)
```

`AppDatabase` 定义在 `data/database/AppDatabase`

```kotlin
@Database(
    entities = [
        LyricEntity::class,
    ],
    version = 27,
    autoMigrations = [
        AutoMigration(from = 25, to = 26, spec = AppDatabase.AutoMigration25To26::class),
        AutoMigration(from = 26, to = 27),
    ],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lyricDao(): ChineseLyricDao

    @DeleteTable(
        tableName = "writings"
    )
    class AutoMigration25To26 : AutoMigrationSpec
}
```

`entities` 数组中的 Entity 会生成相应的数据表。

通过 `@Entity` 注解定义 Entity `data/database/entity/LyricEntity`:

```kotlin
@Entity(tableName = "lyrics")
data class LyricEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val writer: String?,
    val singer: String?,
    val content: String
)
```

`tableName` 为可选项，自定义表名，`@PrimaryKey` 指定主键。

`version` 版本号，无论是 `entities` 数组中增减 Entity, 或者 Entity 中的字段有改动，都应该增加版本号值。

`autoMigrations` 数据库版本迁移，如果只是增加数据表（Entity），则最简单，只需添加 `AutoMigration(from = 1, to = 2),`。
如果`entities` 数组中删除 Entity ，如果不再需要，可以删除数据表，需要定义策略：

```kotlin
@DeleteTable(
    tableName = "writers"
)
class AutoMigration7To8 : AutoMigrationSpec
```

```kotlin
autoMigrations = [
    AutoMigration(from = 7, to = 8, spec = AppDatabase.AutoMigration7To8::class),
],
```

如果某个 Entity 中的字段变动，修改名称、删除等,参考：

```kotlin
@RenameColumn(
    tableName = "chinese_knowledge",
    fromColumnName = "id",
    toColumnName = "rowid"
)
class AutoMigration8To9 : AutoMigrationSpec
```

`exportSchema = true` 导出数据表结构，保存在 `app/schemas/com.hefengbao.jingmo.data.database.AppDatabase` 
目录下。

`version`、`autoMigrations`以及`exportSchema = true`协同确保 App 平滑升级，不然会报错，只能卸载后重新安装。

通过 `@Dao` 注解定义的 `data/database/dao/ChineseLyricDao` 把数据库操作封装为可调用的方法：

```kotlin
@Dao
interface ChineseLyricDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LyricEntity)

    @Query("select * from lyrics where id = :id limit 1")
    fun get(id: Int): Flow<LyricEntity>
    
    @Query("delete from lyrics")
    suspend fun clear()
}
```

导入数据逻辑，打开文件选择器，选择对应的 json 文件，解析后写入数据库。

`ui/screen/settings/ImportDataScreen.kt`：

```kotlin
@Composable
private fun ImportScreen(
    chineseLyricUris: (List<Uri>) -> Unit,
){
    // 定义文件选择器
    val chineseLyricLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            chineseLyricUris(it)
        }
}
```

`OpenMultipleDocuments()` 批量选择。

```kotlin
@Composable
fun ImportRoute(
    viewModel: ImportViewModel = hiltViewModel(),
) {
    ImportScreen(
        chineseLyricUris = { viewModel.lyrics(it) },
    )
}
```

`ui/screen/settings/ImportDataViewModel.kt`：

```kotlin
@HiltViewModel
class ImportViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val json: Json,
    private val repository: ImportRepository,
    private val preference: DatasetPreference,
) : ViewModel() {
    fun lyrics(uris: List<Uri>) {
        viewModelScope.launch {
            uris.forEach { //依次读取解析文件
                json.decodeFromString<List<Lyric>>(readTextFromUri(it)).forEach { lyric ->
                    // 数据写入数据库
                    repository.insertChineseLyric(lyric.asLyricEntity())
                }
            }
        }
    }

    private val contentResolver = context.contentResolver
    
    // 读取json文件并解析的方法封装
    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri): String {
        val stringBuilder = StringBuilder()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    line = reader.readLine()
                }
            }
            inputStream.close()
        }
        return stringBuilder.toString()
    }
}
```

## 依赖注入

方便实列化 Class。

例如：

```kotlin
class ClassA(){
    fun funA(){}
}

class ClassB (){
    val a = ClassA()
    fun funB(){
        a.funA()
    }
}
```

如果需要初始化类的比较多，就会很繁琐。通过依赖注入简化这个过程：

```kotlin
class ClassA(){
    fun funA(){}
}

class ClassB @Inject constructor (a: ClassA){
    fun funB(){
        a.funA()
    }
}
```

官方提供的 Hilt 库，可以方便的实现依赖注入功能。

官方提供了 `@HiltAndroidApp`,`@AndroidEntryPoint`,`@HiltViewModel` 注解，继承自系统的类直接添加对应的注解即可实现:

```kotlin
@HiltAndroidApp
class App : Application() {}
```

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {}
```

```kotlin
@HiltViewModel
class LinkIndexViewModel @Inject constructor(
    private val repository: LinksRepository
) : ViewModel() {
    
}
```

如果我们自己定义的类，则要搭配 `@Inject`、`@Provides`、`@Binds` 等注解实现。

```kotlin
class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
    override suspend fun insertChineseLyric(entity: LyricEntity) =
        database.lyricDao().insert(entity)

    override suspend fun syncChineseLyrics(version: Int): Result<List<Lyric>> = safeApiCall {
        network.chineseLyrics(version)
    }
}
```

在 `constructor` 前面添加 `@Inject` 注解，`constructor` 是必须的[🔗](https://kotlinlang.org/docs/classes.html#constructors)。

在 `di/DataModule`：:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNetworkDatasourceRepository(
        networkDatasourceRepositoryImpl: NetworkDatasourceRepositoryImpl
    ): NetworkDatasourceRepository
}
```

这样在 `ui/screen/settings/SyncDataViewModel.kt` 中就可以使用了：

```kotlin
@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: NetworkDatasourceRepository,
) : ViewModel() {
    fun getDataset() {
        viewModelScope.launch {
            repository.dataset()
        }
    }
}
```

继续看 `NetworkDatasourceRepositoryImpl` 这个类：

```kotlin
class NetworkDatasourceRepositoryImpl @Inject constructor(
    private val network: Network,
    private val database: AppDatabase
) : NetworkDatasourceRepository, SafeApiCall {
  
}
```

`Network` 在 `di/NetworkDatasourceModule` 中做了声明。

```kotlin
@Module
@InstallIn(SingletonComponent::class)
interface NetworkDatasourceModule {
    @Binds
    fun bindsNetwork(
        networkImpl: NetworkImpl
    ): Network
}
```

`AppDatabase` 在 `di/DatabaseModule` 中做了声明。

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = databaseBuilder(
        context,
        AppDatabase::class.java,
        Constant.DB_NAME,
    ).build()
}
```

## 其他

### 类似 `navController::navigateUp` 的函数调用

```kotlin
linkIndexScreen(
    onBackClick = navController::navigateUp
)
```

是下面的简写：

```kotlin
linkIndexScreen(
    onBackClick = {
        navController.navigateUp()
    }
)
```

有参数的情况

```kotlin
onClick = { a: Int, b: String ->
    navController.navigateToScreen(a,b)
}
```

lambda 表达式的参数顺序如果和要调用的函数的参数顺序一致，则可以简写：

```kotlin
onClick = navController::navigateToScreen
```


















