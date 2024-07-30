/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01obb.OBasurtoProgramacionNCapasMaven.Controller;

import com.digis01obb.OBasurtoProgramacionNCapasMaven.DAO.UsuarioDAOImplementation;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Colonia;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Direccion;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Result;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.ResultExcel;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Rol;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.Usuario;
import com.digis01obb.OBasurtoProgramacionNCapasMaven.ML.UsuarioDireccion;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Alien 2
 */
@Controller
@RequestMapping("/cargaMasiva")
public class CargaMasivaController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;

    @GetMapping
    public String CargaInicial() {
        return "CargaMasiva";
    }

    public Date StringToDate(String fechaString) throws ParseException {
        try {
            fechaString = fechaString.replaceAll("\\/", "-");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datoFecha = simpleDateFormat.parse(fechaString);
            return datoFecha;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/txt")
    public String CargaMasivaTXT(@RequestParam("inputFile") MultipartFile fileTXT) throws IOException {
        InputStream inputStream = fileTXT.getInputStream();
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        Result result = new Result();
        bufferReader.readLine();

        try {
            while ((line = bufferReader.readLine()) != null) {
                String[] palabras = line.split("\\|");
                try {
                    UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                    usuarioDireccion.Usuario = new Usuario();
                    usuarioDireccion.Usuario.setUserName(palabras[0]);
                    usuarioDireccion.Usuario.setNombre(palabras[1]);
                    usuarioDireccion.Usuario.setApellidoPaterno(palabras[2]);
                    usuarioDireccion.Usuario.setApellidoMaterno(palabras[3]);
                    usuarioDireccion.Usuario.setEmail(palabras[4]);
                    usuarioDireccion.Usuario.setPassword(palabras[5]);
                    usuarioDireccion.Usuario.setFechaNacimiento(StringToDate(palabras[6]));
//                usuarioDireccion.Usuario.setFechaNacimiento(new Date());
                    usuarioDireccion.Usuario.setSexo(palabras[7]);
                    usuarioDireccion.Usuario.setTelefono(palabras[8]);
                    usuarioDireccion.Usuario.setCelular(palabras[9]);
                    usuarioDireccion.Usuario.setCURP(palabras[10]);
                    usuarioDireccion.Usuario.setImagen(palabras[11]);

                    usuarioDireccion.Usuario.Rol = new Rol();
                    usuarioDireccion.Usuario.Rol.setIdRol(Integer.parseInt(palabras[12]));

                    usuarioDireccion.Direccion = new Direccion();
                    usuarioDireccion.Direccion.setCalle(palabras[13]);
                    usuarioDireccion.Direccion.setNumeroInterior(palabras[14]);
                    usuarioDireccion.Direccion.setNumeroExterior(palabras[15]);
                    usuarioDireccion.Direccion.Colonia = new Colonia();
                    usuarioDireccion.Direccion.Colonia.setIdColonia(Integer.parseInt(palabras[16]));

                    result = usuarioDAOImplementation.AddJPA(usuarioDireccion);
                } catch (Exception ex) {
                    continue;
                }

            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        if (result.correct) {
            return "redirect:/usuario";
        } else {
            return "CargaMasiva";
        }
    }

    @PostMapping("/xlsx")
    public String CargaMasivaCSV(@RequestParam("inputFile") MultipartFile fileExel, Model model, HttpSession session) throws IOException, ParseException {
        if (fileExel != null && !fileExel.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(fileExel.getOriginalFilename());
            if (extension.equals("xlsx")) {
                String root = System.getProperty("user.dir");
                String path = "src/main/resources/static/files";
                String fileName = fileExel.getOriginalFilename();
                String fechaNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String absolutePath = root + "/" + path + "/" + fechaNow + fileName;
                fileExel.transferTo(new File(absolutePath));
                File archivoHistorico = new File(absolutePath);
                List<UsuarioDireccion> usuariosDireccion = ReaderExel(archivoHistorico);

                if (!usuariosDireccion.isEmpty()) {
                    List<ResultExcel> resultsExel = validarExel(usuariosDireccion);
                    if (!resultsExel.isEmpty()) {
                        model.addAttribute("erroresExcel", resultsExel);
                    } else {
                        session.setAttribute("pathExcel", absolutePath);
                        return "procesarExcel";
                    }
                }
            }
        }
        return "CargaMasiva";

    }

    public List<UsuarioDireccion> ReaderExel(File fileExel) throws IOException, ParseException {
        List<UsuarioDireccion> usuariosDireccion = new ArrayList<UsuarioDireccion>();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileExel));
        Sheet workSheet = workbook.getSheetAt(0);
        boolean isFirstRow = true;
        for (Row row : workSheet) {
            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.setUserName((row.getCell(0) == null) ? "" : row.getCell(0).toString());
            usuarioDireccion.Usuario.setNombre((row.getCell(1) == null) ? "" : row.getCell(1).toString());
            usuarioDireccion.Usuario.setApellidoPaterno((row.getCell(2) == null) ? "" : row.getCell(2).toString());
            usuarioDireccion.Usuario.setApellidoMaterno((row.getCell(3) == null) ? "" : row.getCell(3).toString());
            usuarioDireccion.Usuario.setEmail((row.getCell(4) == null) ? "" : row.getCell(4).toString());
            usuarioDireccion.Usuario.setPassword((row.getCell(5) == null) ? "" : row.getCell(5).toString());
            usuarioDireccion.Usuario.setFechaNacimiento((row.getCell(6) == null) ? null : StringToDate(row.getCell(6).toString()));
            usuarioDireccion.Usuario.setSexo((row.getCell(7) == null) ? "" : row.getCell(7).toString());
            usuarioDireccion.Usuario.setTelefono((row.getCell(8) == null) ? "" : LongToString(row.getCell(8).toString()));
            usuarioDireccion.Usuario.setCelular((row.getCell(9) == null) ? "" : LongToString(row.getCell(9).toString()));
            usuarioDireccion.Usuario.setCURP((row.getCell(10) == null) ? "" : row.getCell(10).toString());
            usuarioDireccion.Usuario.setImagen((row.getCell(11) == null) ? null : row.getCell(11).toString());
            usuarioDireccion.Usuario.Rol = new Rol();
            usuarioDireccion.Usuario.Rol.setIdRol((row.getCell(12) == null) ? 0 : StringToInt(row.getCell(12).toString()));
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.setCalle((row.getCell(13) == null) ? "" : row.getCell(13).toString());
            usuarioDireccion.Direccion.setNumeroInterior((row.getCell(14) == null) ? "" : row.getCell(14).toString());
            usuarioDireccion.Direccion.setNumeroExterior((row.getCell(15) == null) ? "" : row.getCell(15).toString());
            usuarioDireccion.Direccion.Colonia = new Colonia();
            usuarioDireccion.Direccion.Colonia.setIdColonia((row.getCell(16) == null) ? 0 : StringToInt(row.getCell(16).toString()));

            usuariosDireccion.add(usuarioDireccion);

        }
        return usuariosDireccion;
    }

    public List<ResultExcel> validarExel(List<UsuarioDireccion> usuariosDireccion) {
        List<ResultExcel> resultsExcel = new ArrayList();
        int row = 1;
        String errorMessage = "";
        for (UsuarioDireccion usuarioDireccion : usuariosDireccion) {
            if (usuarioDireccion.Usuario.getUserName().equals("") || usuarioDireccion.Usuario.getUserName() == null) {
                errorMessage += " El campo nombre de usuario es obligatorio.";
            }

            if (usuarioDireccion.Usuario.getNombre().equals("") || usuarioDireccion.Usuario.getNombre() == null) {
                errorMessage += " El campo nombre es obligatorio.";
            }
            if (SoloLetras(usuarioDireccion.Usuario.getNombre()).equals("ErrorStringSoloLetras")) {
                errorMessage += " El campo nombre solo deben tener caracteres alfaveticos.";
            }

            if (usuarioDireccion.Usuario.getApellidoPaterno().equals("") || usuarioDireccion.Usuario.getApellidoPaterno() == null) {
                errorMessage += " El campo apellido paterno es obligatorio.";
            }
            if (SoloLetras(usuarioDireccion.Usuario.getApellidoPaterno()).equals("ErrorStringSoloLetras")) {
                errorMessage += " El campo apellido paterno solo deben tener caracteres alfaveticos.";
            }

            if (usuarioDireccion.Usuario.getApellidoMaterno().equals("") || usuarioDireccion.Usuario.getApellidoMaterno() == null) {
                errorMessage += " El campo apellido materno es obligatorio.";
            }
            if (SoloLetras(usuarioDireccion.Usuario.getApellidoMaterno()).equals("ErrorStringSoloLetras")) {
                errorMessage += " El campo apellido materno solo deben tener caracteres alfaveticos.";
            }
            if (usuarioDireccion.Usuario.getEmail().equals("") || usuarioDireccion.Usuario.getEmail() == null) {
                errorMessage += " El campo email es obligatorio.";
            }
            if (usuarioDireccion.Usuario.getPassword().equals("") || usuarioDireccion.Usuario.getPassword() == null) {
                errorMessage += " El campo email es obligatorio.";
            }
            if (usuarioDireccion.Usuario.getFechaNacimiento() == null) {
                errorMessage += " El campo fecha de nacimiento es obligatorio y debe contener un formato correcto (yyyy-mm-dd).";
            }
            if (usuarioDireccion.Usuario.getSexo().equals("") || usuarioDireccion.Usuario.getSexo() == null) {
                errorMessage += " El campo sexo es obligatorio.";
            }
            if (usuarioDireccion.Usuario.getTelefono().equals("") || usuarioDireccion.Usuario.getTelefono() == null) {
                errorMessage += " El campo telefono es obligatorio, con solo caracteres numericos y 10 digitos.";
            }
            if (SoloNumeros(usuarioDireccion.Usuario.getTelefono()).equals("ErrorStringSoloNumeros")) {
                errorMessage += " El campo telefono debe tener 10 digitos.";
            }
            if (usuarioDireccion.Usuario.getCelular().equals("") || usuarioDireccion.Usuario.getCelular() == null) {
                errorMessage += " El campo celular es obligatorio con solo caracteres numericos y 10 digitos.";
            }
            if (SoloNumeros(usuarioDireccion.Usuario.getCelular()).equals("ErrorStringSoloNumeros")) {
                errorMessage += " El campo celular debe tener 10 digitos.";
            }
            if (usuarioDireccion.Usuario.getCURP().equals("") || usuarioDireccion.Usuario.getCURP() == null) {
                errorMessage += " El campo CURP es obligatorio.";
            }
            if (ValidationCURP(usuarioDireccion.Usuario.getCURP()).equals("ErrorStringCURP") || usuarioDireccion.Usuario.getCURP() == null) {
                errorMessage += " El campo CURP no contiene un formto valido.";
            }
            if (usuarioDireccion.Usuario.Rol.getIdRol() == 0) {
                errorMessage += " El campo IdRol es obligatorio y debe ser un numero entero.";
            }
            if (usuarioDireccion.Direccion.getCalle().equals("") || usuarioDireccion.Direccion.getCalle() == null) {
                errorMessage += " El campo calle es obligatorio.";
            }
            if (usuarioDireccion.Direccion.getNumeroInterior().equals("") || usuarioDireccion.Direccion.getNumeroInterior() == null) {
                errorMessage += " El campo numero interior es obligatorio.";
            }
            if (usuarioDireccion.Direccion.getNumeroExterior().equals("") || usuarioDireccion.Direccion.getNumeroExterior() == null) {
                errorMessage += " El campo numero exterior es obligatorio.";
            }
            if (usuarioDireccion.Direccion.Colonia.getIdColonia() == 0) {
                errorMessage += " El campo IdColonia es obligatorio y debe ser un numero entero.";
            }

            if (!errorMessage.equals("")) {
                resultsExcel.add(new ResultExcel(row, errorMessage));
            }
            errorMessage = "";
            row++;
        }

        return resultsExcel;
    }

    public String SoloLetras(String stringValidation) {
        String regex = "^[a-zA-ZñÑ\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringValidation);
        if (!stringValidation.equals("")) {
            if (matcher.matches()) {
                return stringValidation;
            } else {
                return "ErrorStringSoloLetras";
            }
        }else{
            return stringValidation;
        }
    }
    public String SoloNumeros(String stringValidation) {
        String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringValidation);
        if (!stringValidation.equals("")) {
            if (matcher.matches()) {
                return stringValidation;
            } else {
                return "ErrorStringSoloNumeros";
            }
        }else{
            return stringValidation;
        }
    }
    public String ValidationCURP(String stringValidation) {
        stringValidation = stringValidation.toUpperCase();
        String regex = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringValidation);
        if (!stringValidation.equals("")) {
            if (matcher.matches()) {
                return stringValidation;
            } else {
                return "ErrorStringCURP";
            }
        }else{
            return stringValidation;
        }
    }

    public String LongToString(String longString) {

        try {
            double doubleDate = Double.parseDouble(longString);
            long longDate = (long) doubleDate;
            return String.valueOf(longDate);
        } catch (Exception e) {
            return "";
        }
    }

    public Integer StringToInt(String stringInt) {
        try {
            double doubleDate = Double.parseDouble(stringInt);
            int intDate = (int) doubleDate;
            return intDate;
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("procesarExcel")
    public String ProcesarExcel(HttpSession session) throws IOException, ParseException {
        String pathExcel = session.getAttribute("pathExcel").toString();
        File fileExcel = new File(pathExcel);
        List<UsuarioDireccion> usuariosDireccion = ReaderExel(fileExcel);
        Result result = new Result();
        boolean isFirstRow = true;
        try {
            for (UsuarioDireccion usuarioDireccionFile : usuariosDireccion) {
                result = usuarioDAOImplementation.AddJPA(usuarioDireccionFile);
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        if (result.correct) {
            return "redirect:/usuario";
        } else {
            return "CargaMasiva";
        }
    }

}
