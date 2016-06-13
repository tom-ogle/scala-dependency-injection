package com.tomogle.dependencyinjection

import com.tomogle.dependencyinjection.ReaderMonad.UserRetrieval
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class ReaderMonadUserRetrievalTest extends FlatSpec with Matchers with MockFactory {

  behavior of "getting a user"

  trait UserRetrievalFixture extends UserRetrieval {
    val id = 987L
    val user = User(id, "a user")
    val userRepositoryMock = mock[UserRepository]
    val userRepositoryStub = stub[UserRepository]
  }

  it should "ask for the user from the repository" in new UserRetrievalFixture {
    (userRepositoryMock get _) expects id
    getUser(id)(userRepositoryMock)
  }

  it should "return the user retrieved from the repository" in new UserRetrievalFixture {
    (userRepositoryStub get _) when * returns user
    val actualUserResult = getUser(id)(userRepositoryStub)
    actualUserResult should equal(user)
  }


}
