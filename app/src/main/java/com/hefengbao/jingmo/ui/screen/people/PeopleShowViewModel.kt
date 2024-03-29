package com.hefengbao.jingmo.ui.screen.people

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.repository.PeopleRepository
import com.hefengbao.jingmo.ui.screen.people.nav.PeopleShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val peopleRepository: PeopleRepository
) : ViewModel() {
    private val args = PeopleShowArgs(savedStateHandle)

    private val _people: MutableStateFlow<PeopleEntity?> = MutableStateFlow(null)
    val people: SharedFlow<PeopleEntity?> = _people

    init {
        viewModelScope.launch {
            if (args.type == "name") {
                peopleRepository.getByName(args.query).collectLatest {
                    _people.value = it
                }
            } else {
                peopleRepository.getById(args.query.toInt()).collectLatest {
                    _people.value = it
                }
            }
        }
    }
}