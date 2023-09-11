package api3

import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient

class  CidadeController {
    static responseFormats = ["json"]
    def LogService


    def list() {
        try {
            def api2BaseUrl = "http://localhost:8080/api2/"
            def client = new RESTClient(api2BaseUrl)
            def response = client.get(path: "cidade/list")

            if (response.status == 200) {
                def jsonResponse = new JsonBuilder()
                jsonResponse {
                    data response.data
                }
                render(contentType: 'application/json', text: jsonResponse.toString())

                // Chamada correta para salvar o log
                def retorno = LogService.salvarLog("Descrição do log")

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

    def get(Long id) {
        try {
            def api2BaseUrl = "http://localhost:8080/api2/"
            def client = new RESTClient(api2BaseUrl)
            def response = client.get(path: "cidade/get/$id")

            if (response.status == 200) {
                def jsonResponse = new JsonBuilder()
                jsonResponse {
                    data response.data
                }
                render(contentType: 'application/json', text: jsonResponse.toString())

                // Chamada correta para salvar o log
                def retorno = LogService.salvarLog("Descrição do log")

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