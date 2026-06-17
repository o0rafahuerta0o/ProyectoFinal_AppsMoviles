package com.rafahuerta.keepnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafahuerta.keepnotes.databinding.FragmentListaNotasBinding

class ListaNotasFragment : Fragment() {

    private var _binding: FragmentListaNotasBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaNotasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotaAdapter(emptyList()) { nota ->
            // Al tocar una nota, navegar a EditarNotaFragment enviando la nota
            val action = ListaNotasFragmentDirections
                .actionListaNotasFragmentToEditarNotaFragment(
                    nota.id, nota.titulo, nota.contenido
                )
            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.todasLasNotas.observe(viewLifecycleOwner) { notas ->
            adapter.actualizarNotas(notas)
        }

        binding.fabAgregarNota.setOnClickListener {
            val action = ListaNotasFragmentDirections
                .actionListaNotasFragmentToEditarNotaFragment(-1, "", "")
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}