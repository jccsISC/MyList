package com.jccsisc.mylist.common.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jccsisc.mylist.common.constants.MyConstant.DB_INVITADOS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providerFirebaseAuth() =  FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providerFirebaseFirestore() = FirebaseFirestore.getInstance()

    @InvitadosCollection
    @Provides
    @Singleton
    fun providerInvitadosCollection(firestore: FirebaseFirestore) = firestore.collection(DB_INVITADOS)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InvitadosCollection
}