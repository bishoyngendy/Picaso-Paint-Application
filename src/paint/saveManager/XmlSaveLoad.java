
package paint.saveManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import paint.models.ShapePicaso;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 2 : Picaso Paint Application (PPA)
 * @author Bishoy Nader & Marc Magdi
 * November 2016
 */
public class XmlSaveLoad {

	/**.
	 * Save the current shapes to an XML file
	 * @param file the file to save to
	 * @param shapes the shapes to save
	 */
	public final void saveToFile(final File file,
			final SavableShapes shapes) {
  		try {
  			JAXBContext jaxbContext = JAXBContext.
  					newInstance(SavableShapes.class);
  	  		Marshaller jaxbMarshaller =
  	  				jaxbContext.createMarshaller();

  	  		// output pretty printed
  	  		jaxbMarshaller.setProperty(
  	  				Marshaller.JAXB_FORMATTED_OUTPUT, true);

  	  		jaxbMarshaller.marshal(shapes, file);
			// jaxbMarshaller.marshal(shapes, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * load shapes from file.
	 * @param file the file
	 * @return the list of shapes.
	 */
	public final List<ShapePicaso> loadFromFile(final File file) {
		JAXBContext jaxbContext;
		SavableShapes shapes = null;
		try {
			jaxbContext = JAXBContext
					.newInstance(SavableShapes.class);
			Unmarshaller jaxbUnmarshaller =
					jaxbContext.createUnmarshaller();
			shapes = (SavableShapes)
					jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		List<ShapePicaso> ret = new ArrayList<>();
		for (int i = 0; i < shapes.getShapesList().size(); i++) {
			ret.add(ShapesDecoder.getShapeFromSavable(
						shapes.getShapesList().get(i)));
		}
		return ret;
	}
}
