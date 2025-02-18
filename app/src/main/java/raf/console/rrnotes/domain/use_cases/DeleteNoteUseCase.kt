package raf.console.rrnotes.domain.use_cases

import raf.console.rrnotes.domain.repository.Repository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke(id: Long) = repository.delete(id)
}