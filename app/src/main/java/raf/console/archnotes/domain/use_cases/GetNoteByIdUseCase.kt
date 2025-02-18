package raf.console.archnotes.domain.use_cases

import raf.console.archnotes.domain.repository.Repository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(id:Long) = repository.getNoteById(id)
}