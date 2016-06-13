package com.tomogle.dependencyinjection

import com.tomogle.dependencyinjection.Cake.{HardCodedUserRetrievalComponent, UserRepositoryComponent, UserRetrievalComponent}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

class CakeUserRetrievalTest extends FlatSpec with Matchers with MockFactory {

  behavior of "getting a user from HardCodedUserRetrieval"

  trait HardCodedUserRetrievalComponentFixture extends HardCodedUserRetrievalComponent {
    val id = 1L
  }

  it should "Return the hard-coded user" in new HardCodedUserRetrievalComponentFixture {

    val expectedUser = User(id, "hard-coded-user")
    val actualUser = userRetrieval.getUser(id)
    actualUser should equal(expectedUser)
  }

  behavior of "getting a user from UserRetrievalComponent"

  trait UserRetrievalComponentFixture extends UserRetrievalComponent with UserRepositoryComponent {
    val id = 1L
    override val userRepository = mock[CakeUserRepository]
  }

  it must "get the user from the repository dependency" in new UserRetrievalComponentFixture {
    userRepository.get _ expects id returning User(id, "user result")
    userRetrieval.getUser(id)
  }
}
