package com.tomogle.dependencyinjection

import com.tomogle.dependencyinjection.FunctionalInjection.UserRetrieval
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class FunctionalInjectionUserRetrievalTest extends FlatSpec with Matchers with MockFactory {

  behavior of "getting a user"

  trait UserRetrievalFixture extends UserRetrieval {
    val id = 123L
    val user = User(id, "my user")
    val userRepositoryMock = mock[UserRepository]
    val userRepositoryStub = stub[UserRepository]
  }

  it should "ask for the user from the repository" in new UserRetrievalFixture {
    (userRepositoryMock get _) expects id
    getUser(id)(userRepositoryMock)
  }

  it should "return the user returned from the repository" in new UserRetrievalFixture {
    (userRepositoryStub get _) when * returns user
    val actualUserResult = getUser(id)(userRepositoryStub)
    actualUserResult should equal(user)
  }

}
