package it.polito.se2.g12.officequeuemanagement.admin

data class AddNewCounterDTO(
    var number:Int,
    var tagNameList: List<String>,
    var description:String?)