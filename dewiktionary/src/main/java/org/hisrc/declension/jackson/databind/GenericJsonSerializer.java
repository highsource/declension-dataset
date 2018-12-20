package org.hisrc.declension.jackson.databind;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import org.hisrc.declension.dto.Inflection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

public class GenericJsonSerializer<T> {

	private final Class<T> type;
	private final File ouputFile;
	private OutputStream os;
	private SequenceWriter sequenceWriter;

	public GenericJsonSerializer(Class<T> type, File ouputFile) {
		Objects.requireNonNull(type, "type must not be null.");
		Objects.requireNonNull(ouputFile, "outputFile must not be null.");
		this.type = type;
		this.ouputFile = ouputFile;
	}

	public void start() throws IOException {
		final ObjectMapper mapper = new ObjectMapper();

		if (os != null) {
			throw new IllegalStateException();
		} else {
			os = new FileOutputStream(ouputFile);
			sequenceWriter = mapper.writerFor(Inflection.class).withDefaultPrettyPrinter().writeValuesAsArray(os);
		}
	}

	public void end() throws IOException {
		if (os == null) {
			throw new IllegalStateException("Invalid state, serializer was not yet started.");
		} else {
			try {
				sequenceWriter.close();
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
			os = null;
			sequenceWriter = null;
		}
	}

	public void serialize(T value) /* throws IOException */ {
		if (sequenceWriter == null) {
			throw new IllegalStateException("Invalid state, serializer was not yet started.");
		} else {
			try {
				sequenceWriter.write(value);
				sequenceWriter.flush();
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
		}
	}
}
