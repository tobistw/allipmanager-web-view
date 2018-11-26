/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ArbeitsbelegService } from 'app/entities/arbeitsbeleg/arbeitsbeleg.service';
import { IArbeitsbeleg, Arbeitsbeleg, Belegstatus } from 'app/shared/model/arbeitsbeleg.model';

describe('Service Tests', () => {
    describe('Arbeitsbeleg Service', () => {
        let injector: TestBed;
        let service: ArbeitsbelegService;
        let httpMock: HttpTestingController;
        let elemDefault: IArbeitsbeleg;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ArbeitsbelegService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Arbeitsbeleg(0, 'AAAAAAA', currentDate, Belegstatus.OFFEN, 'image/png', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        datumLeistung: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Arbeitsbeleg', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        datumLeistung: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datumLeistung: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Arbeitsbeleg(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Arbeitsbeleg', async () => {
                const returnedFromService = Object.assign(
                    {
                        objektNummer: 'BBBBBB',
                        datumLeistung: currentDate.format(DATE_FORMAT),
                        status: 'BBBBBB',
                        arbeitsbeleg: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        datumLeistung: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Arbeitsbeleg', async () => {
                const returnedFromService = Object.assign(
                    {
                        objektNummer: 'BBBBBB',
                        datumLeistung: currentDate.format(DATE_FORMAT),
                        status: 'BBBBBB',
                        arbeitsbeleg: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        datumLeistung: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Arbeitsbeleg', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
