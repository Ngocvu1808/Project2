package com.myclass.controller.teacher;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myclass.entity.CheckinTable;
import com.myclass.entity.User;
import com.myclass.qrcode.QRCode;
import com.myclass.repository.CheckinTableRepository;

@Controller
public class QRCodeController {
	
	@Autowired
	CheckinTableRepository checkinTableRepository;
	@RequestMapping("/createQR")
	public String checkin(QRCode code) throws WriterException, IOException,
	NotFoundException{
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		QRCode.createQRCode("aaa", QRCode.FILE_PATH, "UTF-8", hintMap, 800, 800);
		
		return "_teacher/checkin";
	}
	
	@GetMapping("/checkinForm")
	public String takeCheckin(Model model) {
	model.addAttribute("formCheckin", new CheckinTable());
		return "_student/admissions";
	}
	
	@PostMapping("/checkinForm")
	public String saveCheckin(Model model, @Valid @ModelAttribute("checkin")
			CheckinTable checkinTable, BindingResult error) {
		User checkin = checkinTableRepository.findByEmail(checkinTable.getEmail());
		if (error.hasErrors()) {
			return "_student/admissions";
		}
		if (checkin!= null ) {
			return "_student/admissions";
		}else {
			checkinTableRepository.save(checkinTable);
			System.out.println("Saved!");
			model.addAttribute("message", "Checkin Finished!");
			return "redirect:/createQR";
		}
	}
}
