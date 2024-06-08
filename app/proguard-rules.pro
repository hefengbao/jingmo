# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep DataStore fields
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite* {
   <fields>;
}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# Retrofit
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE



-dontwarn com.chenlb.mmseg4j.ComplexSeg
-dontwarn com.chenlb.mmseg4j.Dictionary
-dontwarn com.chenlb.mmseg4j.MMSeg
-dontwarn com.chenlb.mmseg4j.Seg
-dontwarn com.github.houbb.pinyin.constant.enums.PinyinStyleEnum
-dontwarn com.github.promeg.pinyinhelper.Pinyin$Config
-dontwarn com.github.promeg.pinyinhelper.Pinyin
-dontwarn com.github.stuxuhai.jpinyin.PinyinFormat
-dontwarn com.googlecode.aviator.AviatorEvaluator
-dontwarn com.googlecode.aviator.AviatorEvaluatorInstance
-dontwarn com.hankcs.hanlp.HanLP
-dontwarn com.hankcs.hanlp.seg.Segment
-dontwarn com.huaban.analysis.jieba.JiebaSegmenter$SegMode
-dontwarn com.huaban.analysis.jieba.JiebaSegmenter
-dontwarn com.jfirer.jfireel.expression.Expression
-dontwarn com.mayabot.nlp.segment.Lexer
-dontwarn com.mayabot.nlp.segment.Lexers
-dontwarn com.ql.util.express.ExpressRunner
-dontwarn com.rnkrsoft.bopomofo4j.Bopomofo4j
-dontwarn io.github.logtube.Logtube
-dontwarn io.github.logtube.core.IEventLogger
-dontwarn net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
-dontwarn net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
-dontwarn net.sourceforge.pinyin4j.format.HanyuPinyinToneType
-dontwarn net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
-dontwarn org.ansj.splitWord.Analysis
-dontwarn org.ansj.splitWord.analysis.ToAnalysis
-dontwarn org.apache.commons.jexl3.JexlBuilder
-dontwarn org.apache.commons.jexl3.JexlEngine
-dontwarn org.apache.log4j.Logger
-dontwarn org.apache.logging.log4j.LogManager
-dontwarn org.apache.logging.log4j.Logger
-dontwarn org.apache.lucene.analysis.Analyzer
-dontwarn org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer
-dontwarn org.apdplat.word.segmentation.Segmentation
-dontwarn org.apdplat.word.segmentation.SegmentationAlgorithm
-dontwarn org.apdplat.word.segmentation.SegmentationFactory
-dontwarn org.jboss.logging.Logger
-dontwarn org.lionsoul.jcseg.ISegment$Type
-dontwarn org.lionsoul.jcseg.ISegment
-dontwarn org.lionsoul.jcseg.dic.ADictionary
-dontwarn org.lionsoul.jcseg.dic.DictionaryFactory
-dontwarn org.lionsoul.jcseg.fi.SegmenterFunction
-dontwarn org.lionsoul.jcseg.segmenter.SegmenterConfig
-dontwarn org.mozilla.javascript.Context
-dontwarn org.mvel2.MVEL
-dontwarn org.pmw.tinylog.Level
-dontwarn org.pmw.tinylog.Logger
-dontwarn org.slf4j.ILoggerFactory
-dontwarn org.slf4j.Logger
-dontwarn org.slf4j.LoggerFactory
-dontwarn org.slf4j.helpers.NOPLoggerFactory
-dontwarn org.slf4j.spi.LocationAwareLogger
-dontwarn org.springframework.expression.ExpressionParser
-dontwarn org.springframework.expression.spel.standard.SpelExpressionParser
-dontwarn org.tinylog.Level
-dontwarn org.tinylog.Logger
-dontwarn org.tinylog.configuration.Configuration
-dontwarn org.tinylog.format.AdvancedMessageFormatter
-dontwarn org.tinylog.format.MessageFormatter
-dontwarn org.tinylog.provider.LoggingProvider
-dontwarn org.tinylog.provider.ProviderRegistry