package com.cinepeek.login.di

import com.cinepeek.login.api.LoginService
import com.cinepeek.login.domain.LoginRepository
import com.cinepeek.login.domain.usecase.LoginAsGuestUseCase
import com.cinepeek.login.domain.usecase.LoginAsUserUseCase
import com.cinepeek.login.impl.LoginRepositoryImpl
import com.cinepeek.network.state.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

	@Provides
	fun provideLoginRepository(
		service: LoginService,
		@IoDispatcher dispatcher: CoroutineDispatcher,
	): LoginRepository {
		return LoginRepositoryImpl(service, dispatcher)
	}

	@Provides
	fun provideLoginService(retrofit: Retrofit): LoginService {
		return retrofit.create(LoginService::class.java)
	}

	@Provides
	fun provideLoginUseCase(loginRepository: LoginRepository) = LoginAsUserUseCase(loginRepository)

	@Provides
	fun provideLoginGuestUseCase(loginRepository: LoginRepository) = LoginAsGuestUseCase(loginRepository)

}