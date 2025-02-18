package raf.console.rrnotes.domain.use_cases

import raf.console.rrnotes.data.local.model.Note
import raf.console.rrnotes.domain.repository.Repository
import javax.inject.Inject

class AddUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) = repository.insert(note)
}








