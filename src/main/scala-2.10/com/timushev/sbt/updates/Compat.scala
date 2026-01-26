package com.timushev.sbt.updates

import sbt.Keys._
import sbt._

object Compat {
  implicit class ModuleIDExt(val module: ModuleID) {
    def withRevision0(revision: String): ModuleID = module.copy(revision = revision)
  }

  def createScopedKey[T](settingKey: SettingKey[T], projRef: ProjectRef): ScopedKey[T] = {
    settingKey.in(GlobalScope.in(projRef)).scopedKey
  }

  def setSetting[T](data: sbt.Settings[sbt.Scope], scopedKey: ScopedKey[T], value: T): sbt.Settings[sbt.Scope] = {
    data.set(scopedKey.scope, scopedKey.key, value)
  }

  def inThisBuild[T](key: SettingKey[T]): Def.Initialize[T] = {
    key in ThisBuild
  }
}
