package com.tomogle.dependencyinjection

import scalaz.Reader

object ReaderMonad {

  // Reader[UserRepository, User] is a UserRepository => User function with
  // some sugar (e.g. map and flatMap to allow use in for comprehensions)

  trait UserRetrieval {
    def getUser(id: Long): Reader[UserRepository, User] = Reader { (userRepository: UserRepository) =>
      userRepository.get(id)
    }

    def findUser(username: String): Reader[UserRepository, User] = Reader { (userRepository: UserRepository) =>
      userRepository.find(username)
    }
  }
}
