package com.tomogle.dependencyinjection

object FunctionalInjection {

  trait UserRetrieval {
    def getUser(id: Long): UserRepository => User = _.get(id)

    def findUser(username: String): UserRepository => User = _.find(username)
  }
}
