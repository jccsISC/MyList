package com.jccsisc.mylist.domain.home

import com.jccsisc.mylist.data.model.invitado.InvitadoModel
import com.jccsisc.mylist.data.remote.home.HomeDataSource

class HomeRepoImpl(private val homeDataSource: HomeDataSource): HomeRepo {
    override suspend fun getInvitadosList() = homeDataSource.getInvitadosList()
    override suspend fun registerAsistencia(invitadoModel: InvitadoModel) = homeDataSource.registerAsistencia(invitadoModel)
}