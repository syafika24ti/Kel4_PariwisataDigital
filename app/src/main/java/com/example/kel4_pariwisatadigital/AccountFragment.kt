import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.kel4_pariwisatadigital.LoginActivity
import com.example.kel4_pariwisatadigital.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private var _bindingInstance: FragmentAccountBinding? = null
    private val binding get() = _bindingInstance!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _bindingInstance = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Membaca SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Traveler")
        val email = sharedPref.getString("email", "email@domain.com")
        val isNotifEnabled = sharedPref.getBoolean("notif_enabled", true)

        // 2. Memasang data ke UI
        binding.tvAccountName.text = username
        binding.tvAccountEmail.text = email
        binding.switchNotification.isChecked = isNotifEnabled

        // 3. Menyimpan preferensi Notifikasi
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            sharedPref.edit().putBoolean("notif_enabled", isChecked).apply()
            val status = if (isChecked) "diaktifkan" else "dimatikan"
            Toast.makeText(requireContext(), "Notifikasi $status", Toast.LENGTH_SHORT).show()
        }

        // 4. FITUR LOGOUT (Pastikan tombol di XML bernama android:id="@+id/btnLogout")
        binding.btnLogout.setOnClickListener {
            // Tampilkan AlertDialog konfirmasi
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")

            builder.setPositiveButton("Ya") { dialog, _ ->
                // Hapus data login
                sharedPref.edit().clear().apply()

                // Hapus juga status tutorial agar bisa muncul lagi jika login ulang (opsional)
                val tutorialPref = requireContext().getSharedPreferences("tutorial_pref", Context.MODE_PRIVATE)
                tutorialPref.edit().clear().apply()

                dialog.dismiss()

                // Pindah ke LoginActivity dan bersihkan back stack
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingInstance = null
    }
}