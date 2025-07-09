import axios, { type AxiosInstance, type AxiosResponse } from "axios";

export class Api {
  private axios: AxiosInstance;

  constructor(baseURL = "http://localhost:8080/api") {
    this.axios = axios.create({
      baseURL,
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  // Example GET request: get all letters (adjust endpoint as needed)
  async getLetters(): Promise<any[]> {
    const response: AxiosResponse<any[]> = await this.axios.get("/letters");
    return response.data;
  }

  // Example POST request: create an letter
  async createLetter(letter: any): Promise<any> {
    const response: AxiosResponse<any> = await this.axios.post("/letters", letter);
    return response.data;
  }

  // Example GET by id
  async getLetterById(id: number | string): Promise<any> {
    const response: AxiosResponse<any> = await this.axios.get(`/letters/${id}`);
    return response.data;
  }

  // Example PUT to update an letter
  async updateLetter(id: number | string, letter: any): Promise<any> {
    const response: AxiosResponse<any> = await this.axios.put(`/letters/${id}`, letter);
    return response.data;
  }

  // Example DELETE
  async deleteLetter(id: number | string): Promise<void> {
    await this.axios.delete(`/letters/${id}`);
  }
}
