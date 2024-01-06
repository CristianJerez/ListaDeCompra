package cl.cjerez.listadecompras.data.modelo

import java.io.Serializable

data class Compra (
    val id:String,
    val descripcion:String,
    var seleccionado:Boolean
): Serializable