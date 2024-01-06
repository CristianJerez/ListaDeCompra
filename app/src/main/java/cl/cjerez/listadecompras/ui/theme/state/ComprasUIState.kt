package cl.cjerez.listadecompras.ui.theme.state

import cl.cjerez.listadecompras.data.modelo.Compra

data class ComprasUIState(
    val mensaje: String = "",
    val compras: List<Compra> = listOf<Compra>()
)