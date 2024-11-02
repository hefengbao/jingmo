/*
 *  This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 *  For the full copyright and license information, please view the LICENSE
 *  file that was distributed with this source code.
 */

package com.hefengbao.jingmo.ui.screen.china.worldcultureheritage

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.common.storage.AndroidImageDownloader
import com.hefengbao.jingmo.data.repository.china.WorldCultureHeritageRepository
import com.hefengbao.jingmo.ui.screen.china.worldcultureheritage.nav.ChinaWorldCultureHeritageShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorldCultureHeritageShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: WorldCultureHeritageRepository,
    private val imageDownloader: AndroidImageDownloader,
) : ViewModel() {
    private val args = ChinaWorldCultureHeritageShowArgs(savedStateHandle)

    val worldCulturalHeritageEntity = repository.get(args.id.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun downloadImage(
        url: String,
        onSuccess: (Uri) -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch {
            imageDownloader.downloadImage(url).onSuccess(onSuccess).onFailure(onFailure)
        }
    }
}