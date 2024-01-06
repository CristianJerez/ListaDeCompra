package cl.cjerez.listadecompras.data

import android.util.Log
import cl.cjerez.listadecompras.data.modelo.Compra
import kotlinx.coroutines.flow.MutableStateFlow

class CompraMemoryDataSource {
    private val _compras = mutableListOf<Compra>()
    private val _Alfabeto = MutableStateFlow(false)
    private val _MostrarItems = MutableStateFlow(false)

    init {

    }

    fun obtenerTodas(): List<Compra> {
        return _compras
    }

    fun insertar(vararg compras: Compra) {
        _compras.addAll(compras.asList())
    }

    fun eliminar(compra: Compra) {
        _compras.remove(compra)
        Log.v("DataSource", _compras.toString())
    }

    fun actualizarChecked(id: String, ischecked: Boolean) {
        _compras?.find { it.id == id }?.seleccionado = ischecked
    }

    fun getMostrarItems(): Boolean {
        return _MostrarItems.value
    }

    fun setMostrarItems(seleccion: Boolean) {
        _MostrarItems.value = seleccion
    }

    fun getAlfabeto(): Boolean {
        return _Alfabeto.value
    }

    fun setAlfabeto(seleccion: Boolean) {
        _Alfabeto.value = seleccion
    }

}