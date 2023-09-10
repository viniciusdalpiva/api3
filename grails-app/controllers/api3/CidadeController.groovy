package api3

import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient

class CidadeController {
    static responseFormats = ["json"]

    def show(Long id) {
        try {
            def api2BaseUrl = "http://localhost:8080/api2/"
            def client = new RESTClient(api2BaseUrl)
            def response = client.get(path: "cidade/show/$id")

            if (response.status == 200) {
                def jsonResponse = new JsonBuilder()
                jsonResponse {
                    data response.data
                }
                render(contentType: 'application/json', text: jsonResponse.toString())
            } else {
                render(status: response.status, contentType: 'application/json') {
                    error: "Erro ao buscar a cidade na API2."
                }
            }
        } catch (Exception e) {
            render(status: 500, contentType: 'application/json') {
                error: "Erro ao processar a requisição."
            }
        }
    }
}
