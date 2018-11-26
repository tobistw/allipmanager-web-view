/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AllipmanagerwebviewTestModule } from '../../../test.module';
import { TblFunkmessungComponent } from 'app/entities/tbl-funkmessung/tbl-funkmessung.component';
import { TblFunkmessungService } from 'app/entities/tbl-funkmessung/tbl-funkmessung.service';
import { TblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';

describe('Component Tests', () => {
    describe('TblFunkmessung Management Component', () => {
        let comp: TblFunkmessungComponent;
        let fixture: ComponentFixture<TblFunkmessungComponent>;
        let service: TblFunkmessungService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AllipmanagerwebviewTestModule],
                declarations: [TblFunkmessungComponent],
                providers: []
            })
                .overrideTemplate(TblFunkmessungComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TblFunkmessungComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TblFunkmessungService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TblFunkmessung(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tblFunkmessungs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
