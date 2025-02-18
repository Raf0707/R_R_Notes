package raf.console.rrnotes.domain.use_cases

import raf.console.rrnotes.data.local.model.Note
import raf.console.rrnotes.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke():Flow<List<Note>> =
        repository.getAllNotes()
}