package com.jk.learn.learnjpa.domain.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExport.Action;
import org.hibernate.tool.schema.TargetType;
import org.reflections.Reflections;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateExporter {

	private static final String OUTPUT_FILE = "schema-{}.sql";
	private static final String DIALECT = org.hibernate.dialect.MySQL57Dialect.class.getName();

	private final List<String> entityPackages;

	private HibernateExporter(List<String> entityPackages) {
		this.entityPackages = entityPackages;
	}

	/**
	 * Execution stars here.
	 */
	public static void main(String[] args) {
		final List<String> entityPackages = Collections.singletonList("com.jk.learn.learnjpa");

		HibernateExporter exporter = new HibernateExporter(entityPackages);
		exporter.export();
	}

	private void export() {
		SchemaExport export = new SchemaExport();
		final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");
		final String replacement = LocalDateTime.now().format(dateTimeFormatter);
		final String outputFile = OUTPUT_FILE.replace("{}", replacement);
		export.setOutputFile(outputFile);
		export.setFormat(true);
		export.setDelimiter(";");
		EnumSet<TargetType> types = EnumSet.of(TargetType.SCRIPT);
		Metadata metadata = createMetadataSources().buildMetadata();
		export.execute(types, Action.CREATE, metadata);
		log.info("Output written to file " + outputFile);
	}

	private MetadataSources createMetadataSources() {
		MetadataSources metadata = new MetadataSources(
				new StandardServiceRegistryBuilder()
						.applySetting("hibernate.dialect", DIALECT)
						.build());

		for (String entityPackage : entityPackages) {
			final Reflections reflections = new Reflections(entityPackage);
			for (Class<?> cl : reflections.getTypesAnnotatedWith(MappedSuperclass.class)) {
				metadata.addAnnotatedClass(cl);
				log.info(String.format("Mapped = %s", cl.getName()));
			}
			for (Class<?> cl : reflections.getTypesAnnotatedWith(Entity.class)) {
				metadata.addAnnotatedClass(cl);
				log.info(String.format("Mapped = %s", cl.getName()));
			}
		}
		return metadata;
	}

}
