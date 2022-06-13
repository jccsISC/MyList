package com.jccsisc.mylist.ui.fragments.add

import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.viewModels
import com.jccsisc.mylist.R
import com.jccsisc.mylist.common.base.BaseFragment
import com.jccsisc.mylist.data.remote.add.AddDataSource
import com.jccsisc.mylist.databinding.FragmentAddBinding
import com.jccsisc.mylist.domain.add.AddRepoImpl
import com.jccsisc.mylist.presentation.add.AddVMFactory
import com.jccsisc.mylist.presentation.add.AddViewModel
import com.jccsisc.mylist.utils.showToast
import com.jccsisc.mylist.utils.showView

class AddFragment : BaseFragment<FragmentAddBinding>(), AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {

    var progreso = 0
    var padrinoDe = ""
    var numeroMesa = ""
    var parentezco = 0
    var role = 0

    val viewModel by viewModels<AddViewModel> {
        AddVMFactory(AddRepoImpl(AddDataSource()))
    }

    override fun getLayout() = R.layout.fragment_add

    override fun initView() {
        initElements()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()

        if (parent?.adapter?.count!! >= 10) {
            numeroMesa = item.substring(7, item.length)
        } else {
            padrinoDe = item
        }

    }

    fun validData(name: String, num: String, localidad: String, email: String, parentezco: Boolean, role: Boolean): Boolean {
        with(mBinding) {

            if (name.isEmpty()) {
                tieNombre.error = "El nombre está vacío"; return true
            }
            /*if (num.isEmpty()) {
                tieTelefono.error = "El número está vacío"; return true
            }*/
            /*if (localidad.isEmpty()) {
                tieLocalidad.error = "La localidad está vacía"; return true
            }*/
            /*if (email.isEmpty()) {
                tieCorreo.error = "El correo está vacío"; return true
            }*/

            if (parentezco) {
                showToast("Selecciona un parentezco")
                return true
            }

            if (role) {
                showToast("Selecciona un role")
                return true
            }
        }

        return false
    }

    override fun onCheckedChanged(radioGroup: RadioGroup?, radioButton: Int) = with(mBinding) {
        when(radioButton) {
            rbFamilia.id -> showToast("RadioButton: $radioButton")
            rbAmigo.id -> showToast("RadioButton: $radioButton")
            rbPadrino.id -> {
                showToast("RadioButton: $radioButton")
                tilOpciones.showView()
            }
            rbInvitado.id -> showToast("RadioButton: $radioButton")
        }
    }
}