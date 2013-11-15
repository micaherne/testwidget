/*
 * This file would be included in all packages.
 * 
 * The getFeatureApi() function enables the use of a local service URL
 * (i.e. when the client pages have been opened from the same server
 * location as the service is hosted), but if there is an mpegasus
 * object this will be allowed to manage access to features.
 * 
 * This is designed to ensure that the function will only be defined
 * once, although this could cause issues if different widgets had 
 * different versions of this function in the same page.
 * 
 */
if (typeof window.getFeatureApi === 'undefined') {
	window.getFeatureApi = function(uri, local) {
		if (typeof window.mpegasus !== 'undefined') {
			return window.mpegasus.getFeatureApi(uri);
		} else {
			return local;
		}
	};
}
