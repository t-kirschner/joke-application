import { describe, it, expect, test } from 'vitest';
import {checkInput} from './UserInterface.vue';


describe('Check Input', () => {
    it('should return true for user input English', () => {
        expect(checkInput("1")).toBe(true);    
    })

    it('should return true for user input German', () => {
        expect(checkInput("2")).toBe(true);
    })

    it('should return false for any other user input', () => {
        expect(checkInput("10")).toBe(false);
    })
})