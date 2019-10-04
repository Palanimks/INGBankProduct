package com.product.app.util;

import java.io.FileInputStream;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.product.app.dto.UploadFileResponseDto;
import com.product.app.entity.Product;
import com.product.app.entity.ProductCategory;
import com.product.app.repository.ProductCategoryRepository;
import com.product.app.repository.ProductRepository;

@Component

public class ExcelImportToDB {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public UploadFileResponseDto loadDataToDB(MultipartFile file) {

        UploadFileResponseDto response = new UploadFileResponseDto();
        FileInputStream input = null;
        XSSFSheet sheet = null;
        XSSFWorkbook workbook = null;
        try {

            workbook = new XSSFWorkbook(file.getInputStream());

            sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();


            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0)
                    continue; 


                
                Iterator<Cell> cellIterator = row.cellIterator();


                String categoryName = "";
                String productName = "";


                String productDescription = "";

                Product product = new Product();
                ProductCategory productCategoryRepo = new ProductCategory();


                Cell cell = cellIterator.next();
                if (cellIterator.hasNext()) {
                    Cell cell1 = cellIterator.next();

                    categoryName = cell1.getStringCellValue();

                }

                if (cellIterator.hasNext()) {
                    Cell cell3 = cellIterator.next();
                    productName = cell3.getStringCellValue();

                }
                if (cellIterator.hasNext()) {
                    Cell cell4 = cellIterator.next();
                    productDescription = cell4.getStringCellValue();

                }

                productCategoryRepo.setCategoryName(categoryName);
                ProductCategory productCategory2 = productCategoryRepository.save(productCategoryRepo);

               
                product.setCategoryId(productCategory2.getCategoryId());
                product.setProductName(productName);
                product.setProductDescription(productDescription);

              productRepository.save(product);
             

                response.setMessage("success");
                response.setStatusCode(200);

            }

        } catch (Exception e) {
            
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (input != null) {
                    input.close();
                }
                sheet = null;
            } catch (Exception e) {
                //logger.error(this.getClass().getName() + " loadDataToDB finally block : " + e.getMessage());
            }

        }
        return response;
    }

}


