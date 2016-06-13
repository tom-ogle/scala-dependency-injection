package com.tomogle.dependencyinjection

import com.tomogle.dependencyinjection.Implicits.UserRetrieval
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class ImplicitsUserRetrievalTest extends FlatSpec with Matchers with MockFactory {

  behavior of "getting a user"

  trait UserRetrievalFixture extends UserRetrieval {
    val id = 567L
    val user = User(id, "a user")
  }

  trait UserRetrievalFixtureMockRepository extends UserRetrievalFixture {
    implicit val userRepository = mock[UserRepository]
  }

  trait UserRetrievalFixtureStubRepository extends UserRetrievalFixture {
    implicit val userRepository = stub[UserRepository]
  }

    it should "ask for the user from the repository" in new UserRetrievalFixtureMockRepository {
    (userRepository get _) expects id
    getUser(id)
  }

  it should  "return the user returned from the repository" in new UserRetrievalFixtureStubRepository {
    (userRepository get _) when * returns user
    val actualUserResult = getUser(id)
    actualUserResult should equal(user)
  }

}
