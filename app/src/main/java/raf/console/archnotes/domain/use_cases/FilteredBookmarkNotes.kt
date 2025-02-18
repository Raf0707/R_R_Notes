package raf.console.archnotes.domain.use_cases

import raf.console.archnotes.data.local.model.Note
import raf.console.archnotes.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilteredBookmarkNotes @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getBookMarkedNotes()
    }
}