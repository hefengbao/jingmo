package com.hefengbao.jingmo.ui.screen.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.jingmo.data.database.entity.PeopleEntity
import com.hefengbao.jingmo.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleIndexViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private val _people: MutableStateFlow<PeopleEntity?> = MutableStateFlow(null)
    val people: SharedFlow<PeopleEntity?> = _people

    fun getRandomPeople() {
        viewModelScope.launch {
            peopleRepository.getRandom().collectLatest {
                _people.value = it
            }
        }
    }
}