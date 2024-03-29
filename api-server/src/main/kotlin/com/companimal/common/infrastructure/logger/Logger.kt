package com.companimal.common.infrastructure.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> Logger(): Logger = LoggerFactory.getLogger(T::class.java)