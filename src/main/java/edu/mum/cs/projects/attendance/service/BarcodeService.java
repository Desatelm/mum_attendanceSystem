package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;

public interface BarcodeService {

	List<BarcodeRecord> getBarcodeRecordsList();

	List<BarcodeRecord> getBarcodeRecordsList(LocalDate startDate, LocalDate endDate);

	// fire group
	List<BarcodeRecord> getBarcodeRecordsListByDateAndStudentID(Date startDate, String studentId);

	void deleteBarcodeRecordByBarcodeID(Long barcodeid);

	void addBarcodeRecordList(LocalDate date, String studentId);

}