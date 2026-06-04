package ir.hasanazimi.android_compose_lab.domain

import ir.hasanazimi.android_compose_lab.data.repository.sources.XRepository
import javax.inject.Inject


interface XUseCase {
    fun xFunction() : Nothing
}


class XUseCaseImpl @Inject constructor(
    private val xRepository: XRepository
) : XUseCase{

    override fun xFunction(): Nothing {
        TODO("Not yet implemented")
    }
}