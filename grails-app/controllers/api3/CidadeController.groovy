package api3

import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient

class  CidadeController {
    static responseFormats = ["json"]
    def LogService


    def list() {
        def api2BaseUrl = "http://localhost:8080/api2/"
        def client = new RESTClient(api2BaseUrl)
        def response = client.get(path: "cidade/list")
        def jsonResponse = new JsonBuilder()
        jsonResponse {
            data response.data
        }
        render(contentType: 'application/json', text: jsonResponse.toString())
    }

    def get(Long id) {
        def api2BaseUrl = "http://localhost:8080/api2/"
        def client = new RESTClient(api2BaseUrl)
        def response = client.get(path: "cidade/get/$id")
        def jsonResponse = new JsonBuilder()
        jsonResponse {
            data response.data
        }
        render(contentType: 'application/json', text: jsonResponse.toString())
    }

    def save() {
        def api2BaseUrl = "http://localhost:8080/api2/"
        def client = new RESTClient(api2BaseUrl)
        def response = client.get(path: "cidade/save")
        def jsonResponse = new JsonBuilder()
        jsonResponse {
            data response.data
        }
        render(contentType: 'application/json', text: jsonResponse.toString())
    }

}