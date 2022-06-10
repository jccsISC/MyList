package com.jccsisc.mylist.domain.home

import com.jccsisc.mylist.data.remote.home.HomeDataSource

class HomeRepoImpl(private val homeDataSource: HomeDataSource): HomeRepo {
    override suspend fun getInvitadosList() = homeDataSource.getInvitadosList()
}