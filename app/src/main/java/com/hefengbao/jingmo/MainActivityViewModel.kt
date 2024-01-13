package com.hefengbao.jingmo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.DataSetVersion
import com.hefengbao.jingmo.data.model.AppStatus
import com.hefengbao.jingmo.data.model.asChineseKnowledgeEntity
import com.hefengbao.jingmo.data.model.asChineseWisecrackEntity
import com.hefengbao.jingmo.data.model.asIdiomEntity
import com.hefengbao.jingmo.data.model.asPoemEntity
import com.hefengbao.jingmo.data.model.asPoemSentenceEntity
import com.hefengbao.jingmo.data.model.asPoemTagEntity
import com.hefengbao.jingmo.data.model.asTagEntity
import com.hefengbao.jingmo.data.model.asTongueTwisterEntity
import com.hefengbao.jingmo.data.model.asWriterEntity
import com.hefengbao.jingmo.data.repository.PreferenceRepository
import com.hefengbao.jingmo.data.repository.SyncRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(

) : ViewModel() {
    private val _showLanding: MutableStateFlow<Boolean> = MutableStateFlow(true)
    var showLanding: SharedFlow<Boolean> = _showLanding

    fun closeLanding() {
        viewModelScope.launch {
            delay(1500)
            _showLanding.value = false
        }
    }
}