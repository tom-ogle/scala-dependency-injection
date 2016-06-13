package com.tomogle.dependencyinjection

object Implicits {

  trait UserRetrieval {
    def getUser(id: Long)(implicit userRepository: UserRepository) = userRepository.get(id)
    def findUser(username: String)(implicit userRepository: UserRepository): User = userRepository.find(username)
  }



}
