/*package com.example.demo.excel.config;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.dto.ProductDto;

@Configuration
public class ExcelFileToDatabaseConfig {

	@Bean
	ItemReader<ProductDto> excelStudentReader() {
		PoiItemReader<ProductDto> reader = new PoiItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource("data/students.xlsx"));
		reader.setRowMapper(excelRowMapper());
		return reader;
	}

	private RowMapper<ProductDto> excelRowMapper() {
		BeanWrapperRowMapper<ProductDto> rowMapper = new BeanWrapperRowMapper<>();
		rowMapper.setTargetType(ProductDto.class);
		return rowMapper;
	}

}
*/