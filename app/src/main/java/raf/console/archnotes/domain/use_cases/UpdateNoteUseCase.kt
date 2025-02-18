package raf.console.archnotes.domain.use_cases

import raf.console.archnotes.data.local.model.Note
import raf.console.archnotes.domain.repository.Repository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(note: Note) {
        repository.update(note)
    }
}