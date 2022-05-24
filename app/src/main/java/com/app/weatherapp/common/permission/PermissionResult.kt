package com.app.weatherapp.common.permission

sealed class PermissionResult {
    object Grant : PermissionResult()
    class Deny(val permission: Array<String>) : PermissionResult()
    class Rationale(val permissions: Array<String>) : PermissionResult()
}