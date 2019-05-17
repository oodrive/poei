package com.oodrive.poei.filestorage.crypto.operator;

import java.io.InputStream;
import java.io.OutputStream;


public interface CipherStreamSupplier {

	OutputStream wrapOutputStream(OutputStream output);

	InputStream wrapInputStream(InputStream input);
}
