package com.meilisearch.sdk;

import com.google.gson.JsonArray;

import java.util.List;

/**
 * Wrapper around MeilisearchHttpRequest class to use for Meilisearch documents
 */
class Documents {
	private final MeiliSearchHttpRequest meilisearchHttpRequest;

	protected Documents(Config config) {
		meilisearchHttpRequest = new MeiliSearchHttpRequest(config);
	}

	/**
	 * Retrieves the document at the specified uid with the specified identifier
	 * @param uid Partial index identifier for the requested documents
	 * @param identifier ID of the document
	 * @return A String containing the requested document
	 * @throws Exception if client request causes an error
	 */
	String getDocument(String uid, String identifier) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents/" + identifier;
		return meilisearchHttpRequest.get(requestQuery);
	}

	/**
	 * Retrieves the documents at the specified uid
	 * @param uid Partial index identifier for the requested documents
	 * @return A String containing the requested document
	 * @throws Exception if the client request causes an error
	 */
	String getDocuments(String uid) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents";
		return meilisearchHttpRequest.get(requestQuery);
	}

	/**
	 * Retrieves the documents at the specified uid
	 * @param uid Partial index identifier for the requested documents
	 * @param limit Limit on the requested documents to be returned
	 * @return A String containing the requested document
	 * @throws Exception if the client request causes an error
	 */
	String getDocuments(String uid, int limit) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents?limit=" + limit;
		return meilisearchHttpRequest.get(requestQuery);
	}

	/**
	 * Adds/Replaces a document at the specified uid
	 * @param uid Partial index identifier for the document
	 * @param document String containing the document to add
	 * @return A String containing the added document
	 * @throws Exception if the client request causes an error
	 */
	String addDocuments(String uid, String document) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents";
		return meilisearchHttpRequest.post(requestQuery, document);
	}

	/**
	 * Deletes the document at the specified uid with the specified identifier
	 * @param uid Partial index identifier for the requested document
	 * @param identifier ID of the document
	 * @return The corresponding updateId JSON
	 * @throws Exception if the client request causes an error
	 */
	String deleteDocument(String uid, String identifier) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents/" + identifier;
		return meilisearchHttpRequest.delete(requestQuery);
	}

	/**
	 * Deletes the documents at the specified uid with the specified identifiers
	 * @param uid Partial index identifier for the requested documents
	 * @param identifiers ID of documents to delete
	 * @return The corresponding updateId JSON
	 * @throws Exception if the client request causes an error
	 */
	String deleteDocuments(String uid, List<String> identifiers) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents/" + "delete-batch";
		JsonArray requestData = new JsonArray(identifiers.size());
		identifiers.forEach(requestData::add);

		return meilisearchHttpRequest.post(requestQuery,requestData.toString());
	}

	/**
	 * Deletes all documents at the specified uid
	 * @param uid Partial index identifier for the requested documents
	 * @return The corresponding updateId JSON
	 * @throws Exception if the client request causes an error
	 */
	String deleteAllDocuments(String uid) throws Exception {
		String requestQuery = "/indexes/" + uid + "/documents";
		return meilisearchHttpRequest.delete(requestQuery);
	}
}
