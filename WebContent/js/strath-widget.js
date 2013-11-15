function getFeatureApi(uri, local) {
	if (typeof window.mpegasus !== 'undefined') {
		return window.mpegasus.getFeatureApi(uri);
	} else {
		return local;
	}
}