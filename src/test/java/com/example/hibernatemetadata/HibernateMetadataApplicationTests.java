package com.example.hibernatemetadata;

import com.example.hibernatemetadata.integrators.MetadataExtractorIntegrator;
import org.hibernate.boot.Metadata;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateMetadataApplicationTests {

	private static Logger LOGGER = LoggerFactory.getLogger(HibernateMetadataApplicationTests.class);

	@Test
	public void contextLoads() {
		Metadata metadata = MetadataExtractorIntegrator.INSTANCE.getMetadata();

		for ( PersistentClass persistentClass : metadata.getEntityBindings()) {

			Table table = persistentClass.getTable();

			LOGGER.info( "Entity: {} is mapped to table: {}",
					persistentClass.getClassName(),
					table.getName()
			);

			for(Iterator propertyIterator = persistentClass.getPropertyIterator();
				propertyIterator.hasNext(); ) {
				Property property = (Property) propertyIterator.next();

				for(Iterator columnIterator = property.getColumnIterator();
					columnIterator.hasNext(); ) {
					Column column = (Column) columnIterator.next();

					LOGGER.info( "Property: {} is mapped on table column: {} of type: {}",
							property.getName(),
							column.getName(),
							column.getSqlType()
					);
				}
			}
		}
	}

}
