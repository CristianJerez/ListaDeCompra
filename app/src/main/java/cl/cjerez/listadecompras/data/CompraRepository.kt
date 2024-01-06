package cl.cjerez.listadecompras.data

import cl.cjerez.listadecompras.data.modelo.Compra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.FileInputStream
import java.io.FileOutputStream

class CompraRepository(
    private val compraMemoryDataSource: CompraMemoryDataSource = CompraMemoryDataSource(),
    private val compraDiskDataSource: CompraDiskDataSource = CompraDiskDataSource()
) {
    private val _comprasStream = MutableStateFlow(listOf<Compra>())

    fun getComprasEnDisco(fileInputStream: FileInputStream) {
        val compras = compraDiskDataSource.obtener(fileInputStream)
        insertar(*compras.toTypedArray())
    }

    fun guardarComprasEnDisco(fileOutputStream: FileOutputStream) {
        compraDiskDataSource.guardar(fileOutputStream, compraMemoryDataSource.obtenerTodas())
    }

    fun getComprasStream(): StateFlow<List<Compra>> {
        _comprasStream.update {
            ArrayList(compraMemoryDataSource.obtenerTodas())
        }
        return _comprasStream.asStateFlow()
    }

    fun insertar(vararg compras: Compra) {
        compraMemoryDataSource.insertar(*compras) // spread operator (*)
        getComprasStream()
    }

    fun eliminar(compra: Compra) {
        compraMemoryDataSource.eliminar(compra)
        getComprasStream()
    }

    fun actualizarCheckedTareasEnDisco(id: String, ischecked: Boolean) {
        compraMemoryDataSource.actualizarChecked(id, ischecked)
        getComprasStream()
    }

    fun getAlfabetoEnDisco(): Boolean {
        return compraMemoryDataSource.getAlfabeto()
    }

    fun setAlfabetoEnDisco(seleccion: Boolean) {
        compraMemoryDataSource.setAlfabeto(seleccion)
    }

    fun getMostrarItemsEnDisco(): Boolean {
        return compraMemoryDataSource.getMostrarItems()
    }

    fun setMostrarItemsEnDisco(seleccion: Boolean) {
        compraMemoryDataSource.setMostrarItems(seleccion)
    }
}