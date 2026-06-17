package com.rafahuerta.keepnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rafahuerta.keepnotes.databinding.FragmentEditarNotaBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditarNotaFragment : Fragment() {

    private var _binding: FragmentEditarNotaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotaViewModel by viewModels()
    private val args: EditarNotaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditarNotaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.notaId != -1) {
            binding.etTitulo.setText(args.notaTitulo)
            binding.etContenido.setText(args.notaContenido)
        }

        binding.btnGuardar.setOnClickListener {
            val titulo = binding.etTitulo.text.toString().trim()
            val contenido = binding.etContenido.text.toString().trim()

            if (titulo.isEmpty()) {
                binding.tilTitulo.error = "El título no puede estar vacío"
                return@setOnClickListener
            }

            val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(Date())

            val nota = Nota(
                id = if (args.notaId != -1) args.notaId else 0,
                titulo = titulo,
                contenido = contenido,
                fecha = fecha
            )

            viewModel.guardarNota(nota)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}