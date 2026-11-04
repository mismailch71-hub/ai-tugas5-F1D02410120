import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextArea txtOutput;
    private JButton btnMulai;

    public MainFrame() {
        setTitle("Sistem Pakar Status Bahaya Kebakaran (Forward Chaining)");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblHeader = new JLabel("Sistem Pakar Penentuan Status Bahaya dan Penanganan Kebakaran", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        add(lblHeader, BorderLayout.NORTH);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtOutput.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        JPanel panelBot = new JPanel();
        btnMulai = new JButton("Mulai Konsultasi");
        btnMulai.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnMulai.addActionListener(e -> jalankanKonsultasi());
        panelBot.add(btnMulai);
        add(panelBot, BorderLayout.SOUTH);
    }

    private void jalankanKonsultasi() {
        txtOutput.setText("");
        Set<String> memori = new HashSet<>();

        String[] daftarTanya = {
            "terdeteksi asap tebal",
            "terdengar bunyi alarm kebakaran",
            "terdapat bau korsleting listrik",
            "terdapat bau kebocoran gas",
            "api masih terlokalisir",
            "api sudah merambat ke struktur bangunan"
        };

        for (String fakta : daftarTanya) {
            int jawab = JOptionPane.showConfirmDialog(
                this,
                "Apakah " + fakta + "?",
                "Pertanyaan Kesiapsiagaan Kebakaran",
                JOptionPane.YES_NO_OPTION
            );
            if (jawab == JOptionPane.YES_OPTION) {
                memori.add(fakta);
            }
        }

        txtOutput.append("Fakta Awal: " + memori + "\n\n");
        txtOutput.append("--- Proses Inferensi (Forward Chaining) ---\n");

        Map<String, String[]> alurAlasan = new HashMap<>();
        boolean adaAturanBaru = true;
        int siklus = 1;

        while (adaAturanBaru) {
            adaAturanBaru = false;

            if (!memori.contains("terindikasi adanya titik api") &&
                memori.contains("terdeteksi asap tebal") && memori.contains("terdengar bunyi alarm kebakaran")) {
                    memori.add("terindikasi adanya titik api");
                    alurAlasan.put("terindikasi adanya titk api", new String[]{"R1", "terdeteksi asap tebal, terdengar bunyi alarm kebakaran"});
                    txtOutput.append("Siklus " + siklus + ": R1 terpicu -> terindikasi adanya titik api\n");
                    adaAturanBaru = true;
                    siklus++;
                    continue;
                }

            if (!memori.contains("bahaya pemicu api aktif") &&
                (memori.contains("terdapat bau korsleting listrik") || memori.contains("terdapat bau kebocoran gas"))) {
                    memori.add("bahaya pemicu api aktif");
                    alurAlasan.put("bahaya pemicu api aktif", new String[]{"R2", "terdapat bau korsleting listrik / kebocoran gas"});
                    txtOutput.append("Siklus " + siklus + ": R2 terpicu -> bahaya pemicu api aktif\n");
                    adaAturanBaru = true;
                    siklus++;
                    continue;
                }

            if (!memori.contains("potensi kebakaran meluas") &&
                memori.contains("terindikasi adanya titik api") && memori.contains("bahaya pemicu api aktif")) {
                    memori.add("potensi kebakaran meluas");
                    alurAlasan.put("potensi kebakaran meluas", new String[]{"R3", "terindikasi adanya titik api, bahaya pemicu api aktif"});
                    txtOutput.append("Siklus " + siklus + ": R3 terpicu -> potensi kebakaran meluas\n");
                    adaAturanBaru = true;
                    siklus++;
                    continue;
                }

            if (!memori.contains("Status Siaga 2 (Penanganan Mandiri dengan APAR)") &&
                memori.contains("potensi kebakaran meluas") && memori.contains("api masih terlokalisir")) {
                    memori.add("Status Siaga 2 (Penanganan Mandiri dengan APAR)");
                    alurAlasan.put("Status Siaga 2 (Penanganan Mandiri dengan APAR)", new String[]{"R4", "potensi kebakaran meluas, api masih terlokalisir"});
                    txtOutput.append("Siklus " + siklus + ": R4 terpicu -> Status Siaga 2 (Penanganan Mandiri dengan APAR)\n");
                    adaAturanBaru = true;
                    siklus++;
                    continue;
                }

            if (!memori.contains("Status Siaga 1 (Bahaya Tinggi/Evakuasi dan Panggil Damkar)") &&
                memori.contains("potensi kebakaran meluas") && memori.contains("api sudah merambat ke struktur bangunan")) {
                    memori.add("Status Siaga 1 (Bahaya Tinggi/Evakuasi dan Panggil Damkar)");
                    alurAlasan.put("Status Siaga 1 (Bahaya Tinggi/Evakuasi dan Panggil Damkar)", new String[]{"R5", "potensi kebakaran meluas, api sudah merambat ke struktur"});
                    txtOutput.append("Siklus " + siklus + ": R5 terpicu -> Status Siaga 1 (Bahaya Tinggi/Evakuasi dan Panggil Damkar)\n");
                    adaAturanBaru = true;
                    siklus++;
                    continue;
                }
        }

        txtOutput.append("\n================= Hasil Kesimpulan ===============\n");
        boolean adaKesimpulan = false;
        for (String m : memori) {
            if (m.startsWith("Status Siaga")) {
                txtOutput.append("Kesimpulan: " + m + "\n");
                txtOutput.append("Alasan:\n");
                cetakAlasanGUI(m, alurAlasan, 2);
                adaKesimpulan = true;
            }
        }

        if (!adaKesimpulan) {
            txtOutput.append("Kesimpulan: Status Aman / Potensi Rendah\n");
            txtOutput.append("Alasan: kondisi lingkungan tidak memenuhi kriteria aturan bahaya kebakaran.\n");
        }

        txtOutput.append("\n------------------------------------------------\n");
        txtOutput.append("Catatan Batas Keberlakuan:\n");
        txtOutput.append("Aturan disusun berdasarkan penelitian Jurnal Tataloka UNDIP (Susanti et al., 2020), " +
                         "Jurnal Arsitektura UNS (Tampubolon et al., 2020), serta panduan resmi Dinas Damkar DKI Jakarta. " +
                         "Hasil sistem ini merupakan penilaian awal untuk kesiapsiagaan lingkungan/gedung, bukan keputusan resmi dinas terkait.");
    }

    private void cetakAlasanGUI(String target, Map<String, String[]> alur, int indent) {
        if (alur.containsKey(target)) {
            String[] detail = alur.get(target);
            String spasi = " ".repeat(indent);
            txtOutput.append(spasi + target + " <- " + detail[0] + " (" + detail[1] + ")\n");

            String[] faktaPenyebab = detail[1].split(", ");
            for (String f : faktaPenyebab) {
                if (alur.containsKey(f)) {
                    cetakAlasanGUI(f, alur, indent + 2);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}